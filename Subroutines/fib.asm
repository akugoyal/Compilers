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
ble $a0, 1, return


subu $a0, $a0, 1
subu $sp, $sp, 4
sw $ra, ($sp)
jal fib			#put fib(n-1) in t0
move $t0, $v0


subu $a0, $a0, 1
jal fib			#put fib(n-2) in a0
addu $a0, $a0, 2

addu $v0, $v0, $t0	#add the two and put in v0

lw $ra, ($sp)
addu $sp, $sp, 4
jr $ra


return:
move $a0, $v0
addu $a0, $a0, 2
j fib
