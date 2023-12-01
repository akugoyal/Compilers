.text
.globl main

main:
li $v0, 5
syscall
move $a0, $v0

subu $sp, $sp, 4
sw $ra, ($sp)
jal fib
lw $ra, ($sp)
addu $sp, $sp, 4

move $a0, $v0
li $v0, 1
syscall
li $v0, 10
syscall

fib:
bgt $a0, 1, continue
move $v0, $a0
jr $ra
continue:
subu $sp, $sp, 4
sw $ra, ($sp)

subu $a0, $a0, 1
jal fib
move $t0, $v0

subu $a0, $a0, 1
jal fib
addu $a0, $a0, 2

addu $v0, $v0, $t0

lw $ra, ($sp)
addu $sp, $sp, 4
jr $ra
