cat allInputs | while read line
do
	java Main "inputs/$line"
	#echo "inputs/$line"
done 
