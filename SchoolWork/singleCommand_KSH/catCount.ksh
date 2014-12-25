#  Taylor Siebenberg
#  cs352
#  catCount.ksh

#!/bin/ksh

exitstatus=0

if [ "$1" = "-w" ] ; then									# if first argument is -w
	shift
	if [ "$1" = "-c" ] ; then									# if second argument is -c
		shift

		for i in $* ; do											# for each file 
			cat $i														# cat the file to stdout
			wc $i 2> err.txt											# run word count on file
			if [[ -s err.txt ]] ; then									# if error file exits
				echo File \'"$i"\' not found >&2							# echo error message
				exitstatus=1												# change exitstatus to 1
				rm err.txt
			fi
		done
	else														# else only do word count
		for i in $* ; do											# same logic as about, without cat
			wc $i 2> err.txt
			if [[ -s err.txt ]] ; then
				echo File \'"$i"\' not found >&2
				exitstatus=1
				rm err.txt
			fi
		done
	fi

fi

if [ "$1" = "-c" ] ; then										# if the commands are in opposite order
	shift
	if [ "$1" = "-w" ] ; then										# same logic as above
		shift 

		for j in $* ; do
			cat $j 2> err1.txt
			if [[ -s err1.txt ]] ; then
				echo File \'"$1"\' not found >&2
				exitstatus=1
				rm err1.txt	
			else
				wc $j 2> err.txt
			fi

			if [[ -s err.txt ]] ; then
				echo File \'"$i"\' not found >&2
				exitstatus=1
				rm err.txt
			fi
		done	
	else
		for i in $* ; do 
			cat $i
		done
	fi
fi
exit $exitstatus												# exit with exitstatus
