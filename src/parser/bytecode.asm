	.text
	.globl main
	main: #QTSPIM will automatically look for main
	

	li $v0 14
	move $t0 $v0
	li $v0 14
	bne $t0 $v0 endif1
	

	li $v0 14
	move $t0 $v0
	li $v0 14
	beq $t0 $v0 endif2
	li $v0 3
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newLine
	li $v0, 4
	syscall
endif2:
	li $v0 14
	move $t0 $v0
	li $v0 14
	bgt $t0 $v0 endif3
	li $v0 4
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newLine
	li $v0, 4
	syscall
endif3:
	

endif1:
	li $v0 15
	move $t0 $v0
	li $v0 14
	ble $t0 $v0 endif4
	li $v0 5
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newLine
	li $v0, 4
	syscall
endif4:
	

	# future code will go here
	li $v0 10
	syscall # halt
	.data
	newLine:    .asciiz "\n"
