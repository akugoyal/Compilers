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
subu $sp, $sp, 4
sw $ra, ($sp)
#sw $s0, 4($sp)		# Push ra, s0

# Base case
li $v0, 1
ble $a0, 1, done

#lw $ra, ($sp)
#lw $v0, 4($sp)
#addu $sp, $sp, 4	# Pop ra, v0

#jr $ra
#done:
subu $a0, $a0, 1
jal fib
move $t0, $v0		# Put fib(n-1) into t0


subu $a0, $a0, 1
jal fib			# Put fib(n-2) in v0

addu $a0, $a0, 2
addu $v0, $v0, $t0	# Return sum in v0

done:
#move $v0, $a0
lw $ra, ($sp)
#lw $v0, 4($sp)
addu $sp, $sp, 4	# Pop ra, v0

jr $ra


.data
in: .asciiz "Enter a number:"