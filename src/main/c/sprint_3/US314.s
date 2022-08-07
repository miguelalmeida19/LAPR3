.section .data
    .global cargo #matriz
    .global X
    .global Y
    .global Z
    .global capacity
    counter:
        .int 0
    counterocupados:
        .int 0
.section .text
    .global free_occupied_slots
    free_occupied_slots:
        movq $1, %r9    #number of elements
        movq $0, %rcx   #array addr
        leaq cargo(%rip), %rcx

        #the us 313 uses the fixed size of x * y * z for the array
        imull X(%rip), %r9d
        imull Y(%rip), %r9d
        imull Z(%rip), %r9d
        movl capacity(%rip), %r8d        
        movq counter(%rip), %r10
        movq counterocupados(%rip), %rsi

    ciclo:
        cmpq %r9, %r10 #r9 has the size of the array
        je end
        movl (%rcx, %r10, 4), %r11d #move value of array to %r11d
        cmpq $0, %r11
        jne ocupado #if not free jump to ocupado
        incq %r10
        jmp ciclo

    ocupado:
        incq %rsi   #number of occupied slots
        incq %r10   #the counter
        jmp ciclo

    end:
        movq $0, %rax
        subl %esi, %r10d #capacity - occupied = free
        movl %r10d, %eax
        shlq $32, %rax # |----- 32bits - free ---- | ---- 32bits - 0000000000000000000 ----- |
        orq %rsi, %rax # |----- 32bits - free ---- | ---- 32bits - occupied ----- |
        ret 
        
