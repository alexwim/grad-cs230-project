import sys, os, argparse
import subprocess as sp

parser = argparse.ArgumentParser(description='Run some tests.')
parser.add_argument('file', type=str, help='a java file to analyze')
args = parser.parse_args()

def env_check():
	if not 'FINDBUGS_HOME' in os.environ:
		print('Set the environment variable FINDBUGS_HOME to the top-level directory of FindBugs')
		sys.exit(1)

def run_command(command):
	proc = sp.Popen(command, stdout = sp.PIPE)
	proc.communicate()[0]
	return proc.returncode
		
def main():
	env_check()
	
	targetFile = os.path.join(os.path.dirname(os.path.abspath(__file__)), args.file)
	targetJFile = targetFile + '.java'
	targetCFile = targetFile + '.class'
	if not os.path.isfile(targetJFile):
		print('File: ' + targetJFile + ' not found.')
		sys.exit(1)
		
	jarLoc = os.path.join(os.environ.get('FINDBUGS_HOME'), 'lib', 'findbugs.jar')
	if run_command('javac ' + targetJFile) != 0:
		sys.exit(1)
	
	if run_command('java -jar ' + jarLoc + ' ' + targetCFile) != 0:
		sys.exit(1)
		
	os.remove(targetCFile);

if __name__ == '__main__':
	main()