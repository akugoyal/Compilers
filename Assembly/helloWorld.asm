# This program prints "Hello World!" to the console.
# Author: Akul Goyal
# Version: 11-9-2023

.globl helloWorld
helloWorld:
li $v0, 4
la $a0, msg             # Print the message
syscall
li $v0, 10              # Normal program termination
syscall
.data
msg:
.asciiz "Hello World!\n"
