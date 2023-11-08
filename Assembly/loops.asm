.globl loops


loops:

la $a0, start_msg
li $v0, 4
syscall		#Print start message
li $v0, 5
syscall
move $t0, $v0	# Store lower bound in t0
la $a0, upper_msg
li $v0, 4
syscall		#Print upper message
li $v0, 5
syscall
move $t1, $v0	#Store upper bound in t1
la $a0, step_msg
li $v0, 4
syscall		#Print step message
li $v0, 5
syscall
move $t2, $v0	#Store step bound in t2

loop:
bgt $t0, $t1, end
move $a0, $t0
li $v0, 1
syscall		#Print t0
la $a0, new_line
li $v0, 4
syscall
addu $t0, $t0, $t2
j loop


end:
li $v0, 10
syscall



.data
start_msg: 
.asciiz "Enter the starting value: "
upper_msg: 
.asciiz "Enter the upper value: "
step_msg: 
.asciiz "Enter the step value: "
new_line:
.asciiz "\n"
