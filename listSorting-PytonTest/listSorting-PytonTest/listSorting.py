import sys
import string

'''  
  listSorting.py
  @author Trushank Dand
  Date March 26, 2013
  Version 1.0
 '''
# check command line parameters
if(len(sys.argv) < 3):
    print "Usage: listSorting.py <path-to-input-file>/list.txt <path-to-output-file>/result.txt"
    sys.exit(1)  # exit after prompt
    
fileIn = open(sys.argv[1])  # input file
fileOut = open(sys.argv[2], "w+")  # output file
line = fileIn.readline()  # reading input
numbers = line.strip().split(" ")  # splitting input into individual words

'''
swap Swaps content of the two indexes
@param i: First index
@param j: Second index
'''
def swap(i, j):
      temp = numbers[i]
      numbers[i] = numbers[j]
      numbers[j] = temp
      
for i in range(0, len(numbers)):
    for j in range(i + 1, len(numbers)):
            # if both are numbers or if both are words
            if(numbers[i].isdigit() == numbers[j].isdigit()):
                    # if number and i>j swap
                    if(numbers[i].isdigit() == True and int(numbers[i]) > int(numbers[j])):
                           swap(i, j)
                    # if word and i>j swap
                    elif(numbers[i].isdigit() == False and ord(numbers[i][0]) > ord(numbers[j][0])):
                           swap(i, j)
                        
result = ""
# place result in single string
for i in range(0, len(numbers)):  
       result = result + numbers[i] + " "
# remove punctuation
result = result.translate(string.maketrans("", ""), string.punctuation)       

# remove trailing spaces
result = result.strip() 

# write result to file
fileOut.write(result)  

# closing files
fileOut.close()
fileIn.close()     
