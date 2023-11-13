	.text
	.globl main
	main: #QTSPIM will automatically look for main
	

	li $v0 5
	sw $v0 varx
	li $v0 10
	syscall
	la $t0 varx
	lw $v0 ($t0)
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
	newLine:    .asciiz "\n"
	varx:   .word 0
