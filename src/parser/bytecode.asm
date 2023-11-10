	.text
	.globl main
	main: #QTSPIM will automatically look for main
	

	li $v0 1
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newLine
	li $v0, 4
	syscall
	li $v0 2
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newLine
	li $v0, 4
	syscall
	

	li $v0 3
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newLine
	li $v0, 4
	syscall
	

	

	# future code will go here
	li $v0 10
	syscall # halt
	.data
newLine:
	.asciiz "\n"
