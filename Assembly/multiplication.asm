# This program multiplies two numbers and prints the result.
# Author: Akul Goyal
# Version: 11-9-2023

.globl multiply
multiply:
la $a0, first
li $v0, 4
syscall
li $v0, 5
syscall		#read user input first time

move $t0, $v0
la $a0, second
li $v0, 4
syscall
li $v0, 5	#save input to t0 and read input second time
syscall

move $t1, $v0 	#save second number to t1
mult $t0, $t1	#multiply the numbers
mflo $a0	#save result to a0
li $v0, 1	#print a0
syscall

li $v0, 10
syscall

.data
first:
.asciiz "Enter the first number: "
second:
.asciiz "Enter the second number: "
new:
.asciiz "\n"