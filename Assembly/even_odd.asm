.globl evenOdd
evenOdd:
la $a0, promptNum
li $v0, 4
syscall
li $v0, 5
syscall

move $t0, $v0
li $t1, 2
div $t0, $t1
mfhi $t2
beq $t2, $zero, even
beq $t2, 1, odd

even:
la $a0, even_msg
li $v0, 4
syscall
li $v0, 10
syscall

odd:
la $a0, odd_msg
li $v0, 4
syscall
li $v0, 10
syscall

.data
even_msg:
.asciiz "Even\n"
odd_msg:
.asciiz "Odd\n"
promptNum:
.asciiz "Enter a number: "
