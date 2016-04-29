import sys, os, argparse
import subprocess as sp
import glob

parser = argparse.ArgumentParser(description='Run some tests.')
parser.add_argument('dir', type=str, help='a directory of java files to analyze')
parser.add_argument('-fbopts', type=str, dest='fbopts', help='command line options to pass directly FindBugs', default='')
args = parser.parse_args()

def env_check():
	if not 'FINDBUGS_HOME' in os.environ:
		print('Set the environment variable FINDBUGS_HOME to the top-level directory of FindBugs')
		sys.exit(1)

def run_command(command):
	proc = sp.Popen(command, stdout = sp.PIPE)
	proc.communicate()[0]
	return proc.returncode

##
# Compile target java file
# Run target java file through FindBugs
# Remove class file
##
def analyze_file(targetJFile):
	targetCFile = targetJFile[:-5]+'.class'
	
	print("Analyzing file: "+targetJFile)
	fbJarLoc = os.path.join(os.environ.get('FINDBUGS_HOME'), 'lib', 'findbugs.jar')
	print("Compiling...")
	if run_command('javac ' + targetJFile) != 0:
		sys.exit(1)
	
	print("Finding bugs...")
	run_command(' '.join(['java -jar',fbJarLoc,args.fbopts,targetCFile]))
		
	os.remove(targetCFile);
	
def main():
	env_check()
	
	if args.dir[-5:] == '.java':
		analyze_file(args.dir)
	else:
		for filename in glob.iglob(os.path.join(args.dir,'**/*.java'), recursive = True):
			analyze_file(filename)

if __name__ == '__main__':
	main()