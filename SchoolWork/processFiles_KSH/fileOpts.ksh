#!/bin/ksh

c="not selected"									# command line flags
l="not selected"
p="not selected"
f="not selected"
rOut="not selected"
pNum=0
count=0
options="not selected"

touch temp.txt
rFlag=temp.txt										# used to send standard out to this file if selected


while getopts "clp#fr:" optchar ; do				# standard getopts used for command line processing
	case ${optchar} in
		c) 	c="selected"								# set c flag
			options="selected"     
          		;;

		l) 	l="selected"								# set l flag
			options="selected"
			;;

		p) 	p="selected"								# set p flag
			options="selected"
			pNum=${OPTARG}
			;;
		
		f)	f="selected"								# set f flag
			options="selected"
			;;
		
		r)	rOut="selected"								# set r flag
			options="selected"
			rFlag=${OPTARG}
			if [ -f $rFlag ] ; then
				rm "$rFlag" 
			else
				touch "$rFlag"
			fi
			;;
                
      	\?) echo "Usage: ksh $0 [-c] [-l] [-p #] [-f]  [-r filename] [ file ... ]" >&2		# otherwise echo usage
          	exit 1
          	;;

   	esac
done

shift $(( ${OPTIND} - 1 ))									# shift past the command line arguments
touch temp2


if [ "$options" = "selected" ] ; then						# if at least 1 argument was selected

	for i in $* ; do											# for each file

		count=$(($count + 1))										# update count

		if [ "$rOut" = "selected" ] ; then							# if r flag is selected, echo info to temp2
	  		echo Processing file: "$i" >> temp2
			echo >> temp2
		else														# else just echo info to rFlag
			echo Processing file: "$i" >> $rFlag
			echo >> $rFlag
		fi
	
		if [ "$c" = "selected" ] ; then								# if c flag is selected, run word count
			wc $i >> $rFlag			
		fi
	
		if [ "$l" = "selected" ] ; then								# if l flag is selected, run ls -l
			ls -l $i >> $rFlag
		fi
	
		if [ "$p" = "selected" ] ; then 							# if p flag is slected, print output to stdout
			if (("$pNum" == "0" )) ; then								# if pNum = 0, echo entire output
				cat $i >> $rFlag	
			fi
	
			if (("$pNum" > "0")) ; then									# if pNum > 0, use head -n pNum
				head -n "$pNum" $i >> $rFlag	
			fi
		
			if (("$pNum" < "0")) ; then									# if pNum < 0, use tail -n pNum
				tail -n "$pNum" $i >> $rFlag
			fi
		fi
	
		if [ "$f" = "selected" ] ; then								# if f flag is selected, run the input file on a file
			file $i >> $rFlag
		fi	
	
		if (( "$#" > "$count" )) ; then								# if $# > $count echo rflag
			echo >> $rFlag
			echo -------------------- >> $rFlag
			echo >> $rFlag
		fi	
	
	done

	if [ "$rOut" = "not selected" ] ; then							# if rFlag is not selected, cat rFlag, otherwise cat temp2
		cat $rFlag
	else
		cat temp2
	fi

fi

rm temp.txt															# remove temp files
rm temp2
