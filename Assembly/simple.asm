.globl simple
simple:
li $t0, 2
li $t1, 3
addu $t2, $t0, $t1
li $v0, 10
move $a0, $t2
li $v0, 1
syscall

li $v0, 10
syscall