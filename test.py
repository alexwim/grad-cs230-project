import sys, os, argparse, shutil
import subprocess as sp
import glob

# Constants
DIR_BYTECODE = os.path.join(os.path.dirname(os.path.abspath(__file__)), 'ByteCode')

# Arguments
parser = argparse.ArgumentParser(description='Run some tests.')
parser.add_argument('dir', type=str, help='a directory of java files to analyze')
parser.add_argument('-fbopts', type=str, dest='fbopts', help='command line options to pass directly FindBugs', default='')
args = parser.parse_args()

# Code
def env_check():
	if not 'FINDBUGS_HOME' in os.environ:
		print('Set the environment variable FINDBUGS_HOME to the top-level directory of FindBugs')
		sys.exit(1)

def run_command(command, absorbOutput = False):
	if (absorbOutput):
		proc = sp.Popen(command, stdout = sp.PIPE, stderr = sp.PIPE)
		out, err = proc.communicate()
	else:
		proc = sp.Popen(command)
		proc.communicate()
	return proc.returncode

def compile_file(targetJFile):
	print('...'+targetJFile)
	if not os.path.exists(DIR_BYTECODE):
		os.makedirs(DIR_BYTECODE)
	
	fbJarLoc = os.path.join(os.environ.get('FINDBUGS_HOME'), 'lib', 'findbugs.jar')
	if run_command(' '.join(['javac', '-cp '+fbJarLoc, '-d '+DIR_BYTECODE, targetJFile])) > 0:
		return 0
	return 1

def run_findbugs():
	print('Analyzing...')
	fbJarLoc = os.path.join(os.environ.get('FINDBUGS_HOME'), 'lib', 'findbugs.jar')
	run_command(' '.join(['java -jar',fbJarLoc,'-textui',args.fbopts,DIR_BYTECODE]))
	
def main():
	env_check()
	
	print('Compiling...')
	count = 0
	if args.dir[-5:] == '.java':
		count = compile_file(args.dir)
	else:
		for filename in glob.iglob(os.path.join(args.dir,'**/*.java'), recursive = True):
			count += compile_file(filename)
	
	if count > 0:
		run_findbugs()
	
	shutil.rmtree(DIR_BYTECODE)

if __name__ == '__main__':
	main()