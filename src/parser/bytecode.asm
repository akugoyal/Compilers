	.text
	.globl main
	main: #QTSPIM will automatically look for main
	

	li $v0 1
	sw $v0 varcount
startwhile2:
	la $t0 varcount
	lw $v0 ($t0)
	move $t0 $v0
	li $v0 15
	bgt $t0 $v0 endwhile1
	

	la $t0 varcount
	lw $v0 ($t0)
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newLine
	li $v0, 4
	syscall
	li $v0 1
	subu $sp $sp 4
	sw $v0 ($sp)    #push $v0
	la $t0 varcount
	lw $v0 ($t0)
	lw $t0 ($sp)    #pop $t0
	addu $sp $sp 4
	add $v0 $v0 $t0
	sw $v0 varcount
	

	j startwhile2
endwhile1:
	

	# future code will go here
	li $v0 10
	syscall # halt
	.data
	newLine:    .asciiz "\n"
	varcount:   .word 0
