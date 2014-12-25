#	Taylor Siebenberg
#	cs352
#	onePipe.ksh

#!/bin/ksh

if (( "$#" % "2" != "0" )) ; then									# check to see if arguments were passed as pairs
	echo Arguments must be paired >&2
	echo Usage: "$0" cmd1 cmd2 [cmd1 cmd2] ... >&2						# if not call usage and exit with 1
	exit 1
else																# otherwise continue with normal logic
	steps=$(("$#"/2))													# steps = for loop iterator
	for ((i = 0; i < $steps; i++)) ; do
	
		echo Executing: "$1 | $2"					
		$1 2> err1.txt > /dev/null										# send $1 errors to err1.txt
		$1 2> /dev/null | $2 2> err2.txt								# run $1 | $2
		exitstatus=$?													# exitstatus is the exit status of previous command
		echo
		
		if [[ -s err1.txt ]] ; then										# if err1.txt contains info
			echo "$1" produced the following error output:					# output error info to stdout
			cat err1.txt
		else															# else there are no errors
			echo "$1" produced nothing on standard error
		fi
		
		if [[ -s err2.txt ]] ; then										# similar check for err2.txt
			echo "$2" produced the following error output:
			cat err2.txt
		else
			echo "$2" produced nothing on standard error
		fi
	
		echo Exit status of "$2": "$exitstatus" 						# echo the exitstatus
		echo
		echo -------------------- 
		echo	
		
		shift 2		

		rm err1.txt														# remove the error files
		rm err2.txt
	done
fi
