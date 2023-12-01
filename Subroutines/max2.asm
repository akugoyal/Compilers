.text
.globl main

main:
li $v0, 5
syscall
move $a0, $v0

li $v0, 5
syscall
move $a1, $v0

subu $sp, $sp, 4
sw $ra, ($sp)
jal max2
lw $ra, ($sp)
addu $sp $sp, 4

lw $a0, max
li $v0 1
syscall
li $v0 10
syscall

max2:
ble, $a0, $a1, ygt
sw $a0, max
jr $ra

ygt:
sw $a1, max
jr $ra

.data
max: .word 0
