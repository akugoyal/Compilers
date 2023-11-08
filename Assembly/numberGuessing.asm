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
la $a0, win_msg
li $v0, 4
syscall
li $v0, 10
syscall
 
high:
la $a0, high_msg
li $v0, 4
syscall
j loop

low:
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
