.text
.globl main

main:
li $v0, 5
syscall
move $a0, $v0

subu $sp, $sp, 4
sw $ra, ($sp)
jal fact
lw $ra, ($sp)
addu $sp, $sp, 4

lw $a0, n
li $v0, 1
syscall
li $v0, 10
syscall

fact:
beq $a0, $zero, end
lw $t0, n
mult $t0, $a0
mflo $t0
sw $t0, n
subu $a0, $a0, 1
subu $sp, $sp, 4
sw $ra, ($sp)
jal fact
lw $ra, ($sp)
addu $sp, $sp, 4
jr $ra

end:
jr $ra

.data
n: .word 1