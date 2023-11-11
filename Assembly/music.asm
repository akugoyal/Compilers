# This file plays the Jeopardy Theme Song
# Author: Akul Goyal
# Version: 11-10-2023

.data
notes: .word 34
pitches: .word 69, 74, 69, 62, 69, 74, 69, 69, 74, 69, 74, 78, 0, 76, 74, 73, 71, 70, 69, 74, 69, 66, 67, 69, 74, 69, 74, 0, 71, 69, 67, 66, 64, 62
durations: .word 2, 2, 2, 2, 2, 2, 4, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 2, 2, 4, 2, 1, 1, 2, 2, 2, 2, 2
eigth: .word 227
.text
.globl main
# 
main:
li $a2 0		# instrument: piano
li $a3 127		# volume: max

lw $t0 notes		# t0: numNotes
la $t1 pitches		# t1: pitches
la $t2 durations	# t2: duration number
lw $t3 eigth		# t3: quarter length


loop:
beq $t0 0 end

lw $a0 ($t1)	#load/increment pitch
addu $t1 $t1 4

lw $t4 ($t2)
mult $t4, $t3	# multiply length x duratino
mflo $a1	# move to a1
addu $t2 $t2 4	# increment duration

li $v0 33
syscall		# midi out
subu $t0 $t0 1	# decrement num notes

j loop

end:
li $v0 10
syscall
