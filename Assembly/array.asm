.globl arrMain
arrMain:
la $s0, array	#s0 is current location in array
li $t0, 0	#t0 is how many numbers done
li $t1, 0	#t1 is sum of elements
li $t2, 0	#t2 is min
li $t3, 0	#t3 is max

li $t4, 0	#t4 is average
lw $s1, size	#s1 is max number

la $a0, prompt_first
li $v0, 4
syscall
move $a0, $t0
li $v0, 1
syscall
la $a0, prompt_second
li $v0, 4
syscall

li $v0, 5
syscall
move $t5, $v0		#input first number

addu $t1, $t1, $t5
move $t2, $t5
move $t3, $t5		#increment stats

addu $s0, $s0, 4
addu $t0, $t0, 1

loop:
bge $t0, $s1, end

la $a0, prompt_first
li $v0, 4
syscall
move $a0, $t0
li $v0, 1			#print prompt message
syscall
la $a0, prompt_second
li $v0, 4
syscall

li $v0, 5
syscall
move $t5, $v0		# Store input in t5
sw $t5, ($s0)
addu $t0, $t0, 1
addu $t1, $t1, $t5
addu $s0, $s0, 4	#Increment stats
blt $t5, $t2, min	
bgt $t5, $t3, max
j loop

min:
move $t2, $t5		#Change the min value
j loop
max:
move $t3, $t5		#Change the max value
j loop

end:
div $t1, $t0		#Compute the average
mflo $t4

la $a0, sum		#Print the sum
li $v0, 4
syscall
move $a0, $t1
li $v0, 1
syscall
la $a0, new
li $v0, 4
syscall

la $a0, average		#Print the average
li $v0, 4
syscall
move $a0, $t4
li $v0, 1
syscall
la $a0, dot
li $v0, 4
syscall
mfhi $a0
li $v0, 1
syscall
la $a0, new
li $v0, 4
syscall

la $a0, low		#Print the min
li $v0, 4
syscall
move $a0, $t2
li $v0, 1
syscall
la $a0, new
li $v0, 4
syscall

la $a0, high		#Print the max
li $v0, 4
syscall
move $a0, $t3
li $v0, 1
syscall
la $a0, new
li $v0, 4
syscall

li $v0, 10		#Normal termination
syscall

.data
array:
.space 40
size:
.word 10
prompt_first:
.asciiz "Enter the element for index "
prompt_second:
.asciiz " in the array: "
sum:
.asciiz "The sum of the elements in the array is "
average:
.asciiz "The average of the elements in the array is "
low:
.asciiz "The min of the elements in the array is "
high:
.asciiz "The high of the elements in the array is "
new:
.asciiz "\n"
dot:
.asciiz "."
