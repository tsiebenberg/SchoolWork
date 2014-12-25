# 	Taylor Siebenberg
#	CSC 352
#	search.ksh

#! /bin/ksh

#  Information for --man
USAGE="[-?$Id search.ksh, version 1.0]"
USAGE+="[-author?Taylor Siebenberg]"
USAGE+="[-copyright?Copyright (c) September 26, 2014]"

#  Name for --man
USAGE+="[+NAME?search.ksh -- uses fgrep or egrep to search files]"

#  Description for --man
USAGE+="[+DESCRIPTION?The search.ksh script will use either or both of egrep and fgrep to search for a pattern in one or more files. If neither -e nor -f is specified, then the program defaults to using fgrep.]"
USAGE+="[+?The results of the search(es) are printed to stdout by default. The results can be piped to wc by using -w. The results can also be piped through head or tail, and the maximum number of output lines that head or tail will print can be specified.]"
USAGE+="[+?Output can be directed into a file by using the -o option. In this case, no output goes to the screen.]"  

#  Options for --man
USAGE+="[e:egrep?use egrep for searching]"
USAGE+="[f:fgrep?use fgrep for searching]"
USAGE+="[w:wc?pipe output through wc]"
USAGE+="[h:head]#?[lines:=10?pipe the output through head.]"
USAGE+="[t:tail]#?[lines:=10?pipe the output through tail.]"
USAGE+="[o:output]:[filename?re-direct output to filename]"

#  Synopsis for --man
USAGE+=$'\n\npattern [file ...]\n\n'

#  See Also for --man
USAGE+=$'[+SEE ALSO?egrep(1), fgrep(1), head(1), tail(1), wc(1)]'

#  Variables
f="not selected"	#f command line flag
e="not selected"	#e command line flag
w="not selected"   	#w command line flag
h="not selected" 	#h command line flag
t="not selected"	#t command line flag
o="not selected"	#o command line flag

# getopts function to parse command line arguments
while getopts "$USAGE" optchar ; do
	case $optchar in
		f)	f="selected"
			;;
		e)	e="selected"
			;;
		w)	w="selected"
			;;
		h)	h="selected"
			lines=${OPTARG:-"10"} 	#Lines for -n switch on head command
			;;
		t) 	t="selected"
			lines=${OPTARG:-"10"}	#Lines for -n switch on tail command
			;;
		o)	o="selected"
			outFile=${OPTARG}
			if [ -f "$outFile" ] ; then
				rm "$outFFile"
			else
				touch "$outFile"
			fi	
			;;
	esac
done

# shift command line pointer past the command line arguments
shift $(( ${OPTIND} - 1 ))

# Variable
pattern="$1"		#pattern to use for processing 
shift

# Processes each file depending on selected arguments 
for i in $* ; do

	#  Run fgrep with $pattern, and possible pipes, on file $i
	if [ "$f" = "selected" ] ; then
		
		# redirect stdout to $outFile
		if [ "$o" = "selected" ] ; then
			
			#  Pipe results thorgh wc
			if [ "$w" = "selected" ] ; then
				fgrep "$pattern" "$i" | wc >> "$outFile"

			#  Pipe results through head with -n=$lines 
			elif [ "$h" = "selected" ] ; then
				fgrep "$pattern" "$i" | head -n "$lines" >> "$outFile"

			#  Pipe results through tail with -n=$lines
			elif [ "$t" = "selected" ] ; then
				fgrep "$pattern" "$i" | tail -n "$lines" >> "$outFile"

			#  Standard output of fgrep
			else
				fgrep "$pattern" "$i" >> "$outFile"
			fi
		
		# output to stdout
		else
			#  Pipe results thorgh wc
			if [ "$w" = "selected" ] ; then
				fgrep "$pattern" "$i" | wc

			#  Pipe results through head with -n=$lines 
			elif [ "$h" = "selected" ] ; then
				fgrep "$pattern" "$i" | head -n "$lines"

			#  Pipe results through tail with -n=$lines
			elif [ "$t" = "selected" ] ; then
				fgrep "$pattern" "$i" | tail -n "$lines"

			#  Standard output of fgrep
			else
				fgrep "$pattern" "$i"
			fi

		fi
	fi
	
	#  Run egrep with $pattern, and possible pipes, on file $i
	if [ "$e" = "selected" ] ; then
		
		#  Redirect stdout to $outFile
		if [ "$o" = "selected" ] ; then

			#  Pipe results thorgh wc
			if [ "$w" = "selected" ] ; then
				egrep "$pattern" "$i" | wc >> "$outFile"

			#  Pipe results through head with -n=$lines 
			elif [ "$h" = "selected" ] ; then
				egrep "$pattern" "$i" | head -n "$lines" >> "$outFile"

			#  Pipe results through tail with -n=$lines
			elif [ "$t" = "selected" ] ; then
				egrep "$pattern" "$i" | tail -n "$lines" >> "$outFile"

			#  Standard output of egrep
			else
				egrep "$pattern" "$i" >> "$outFile"
			fi

		#  Output to stdout	
		else

			#  Pipe results thorgh wc
			if [ "$w" = "selected" ] ; then
				egrep "$pattern" "$i" | wc

			#  Pipe results through head with -n=$lines 
			elif [ "$h" = "selected" ] ; then
				egrep "$pattern" "$i" | head -n "$lines"

			#  Pipe results through tail with -n=$lines
			elif [ "$t" = "selected" ] ; then
				egrep "$pattern" "$i" | tail -n "$lines"

			#  Standard output of egrep
			else
				egrep "$pattern" "$i"
			fi
		fi
	fi
done
