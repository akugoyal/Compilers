# This program will generate a random number between 1 and 100, inclusive. The user will then be
# prompted to guess the number. If the user guesses the number, the program will terminate. If the
# user guesses too high or too low, the program will prompt the user to guess again
# Author: Akul Goyal
# #Version: 11-9-2023

.globl numberGuess


numberGuess:

li $v0, 42
li $a1, 99
syscall
add $a0, $a0, 1
move $t1, $a0		#Set t1 to the random number

loop:
la $a0, start_msg
li $v0, 4
syscall			#Prompt user

li $v0, 5
syscall	
move $t0, $v0		#Take input

beq $t1, $t0, win
bgt $t0, $t1, high
blt $t0, $t1, low
j loop

win:
la $a0, win_msg             # Print win and normal termination
li $v0, 4
syscall
li $v0, 10
syscall
 
high:                       # Print high and loop
la $a0, high_msg
li $v0, 4
syscall
j loop

low:                        # Print low and loop
la $a0, low_msg
li $v0, 4
syscall
j loop

.data
start_msg:
.asciiz "Guess a number between 1 and 100, inclusive: "
win_msg:
.asciiz "You guessed the number!"
low_msg:
.asciiz "Too low. "
high_msg:
.asciiz "Too high. "
