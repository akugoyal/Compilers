# This program adds two numbers and prints the result.
# Author: Akul Goyal
# Version: 11-9-2023

.globl simple
simple:
li $a0, 2
li $t0, 3
addu $a0, $a0, $t0          # Add two numbers, store in $a0. Then print.
li $v0, 1
syscall

li $v0, 10                  # Normal program termination.
syscall