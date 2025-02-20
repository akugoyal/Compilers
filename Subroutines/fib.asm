# Computes the nth term in the Fibonacci series.
# 
# Author: Akul Goyal
# Version: 12-5-2023
.text
.globl main

main:
la $a0, in
li $v0, 4
syscall				# Print input message

li $v0, 5
syscall
move $a0, $v0			# Take input to a0

subu $sp, $sp, 4
sw $ra, ($sp)			# Push ra

jal fib

lw $ra, ($sp)
addu $sp, $sp, 4		# Pop ra

move $a0, $v0
li $v0, 1
syscall				# Print output

li $v0, 10
syscall				# Normal termination


#----------------------------------------------------------------------------------
# The fib function takes one integer, n, in a0 and returns the nth term of the Fibonacci series in v0
fib:
subu $sp, $sp, 12
sw $ra, ($sp)
sw $a0, 4($sp)
sw $s0, 8($sp)			# Push ra, a0, s0

blt $a0, 2, base		# Base case

subu $a0, $a0, 1
jal fib	
move $s0, $v0			# fib(n-1) into s0


subu $a0, $a0, 1
jal fib				# fib(n-2) into v0

addu $v0, $v0, $s0		# fib(n-1) + fib(n-2) into v0

end:
lw $ra, ($sp)
lw $a0, 4($sp)
lw $s0, 8($sp)
addu $sp, $sp, 12		# Pop ra, a0, s0
jr $ra				# Return

base:
move $v0, $a0
j end				# Return a0


.data
in: .asciiz "Enter a number:"
