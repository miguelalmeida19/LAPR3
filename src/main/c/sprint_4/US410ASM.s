.section .data
    .equ DATA_SIZE, 16 #bytes
    .equ CONTAINER_ID, 0
    .equ TEMPERATURE, 4
    .equ POSX, 8
    .equ POSY, 10
    .equ POSZ, 12
.section .text
    .global isrefrigerated
    isrefrigerated:
        #rdi pointer to array
        #rsi size
        #rdx x
        #rcx y
        #r8 z
        movq $0, %rax
        movq $0, %r10
        movq $0, %r11
        movq $0, %r12
                jmp ciclo

    #typedef struct {
    #    int container_ID;
    #    int temperature;
    #    short posX;
    #    short posY;
    #    short posZ;
    #} Container;
    
    #já percorre o array e obtem os valores.
    ciclo:
        cmpl %esi, %eax # se chegamos ao fim do loop...
        je endcontainernotfound

        movl TEMPERATURE(%rdi), %r9d
        movswl POSX(%rdi), %r10d
        movswl POSY(%rdi), %r11d
        movswl POSZ(%rdi), %r12d
        cmpl %r10d, %edx
        je xencontrado
        addq $16, %rdi
        incl %eax
        jmp ciclo
    xencontrado:
        addq $16, %rdi

        incl %eax
        cmpl %r11d, %ecx
        jne ciclo
        cmpl %r12d, %r8d
        jne ciclo
        # refrigerado: 7º

        cmpl $-5, %r9d
        je refrigerado
        movq $0, %rax
        jmp end

    refrigerado:
        movq $1, %rax
        jmp end
    
    endcontainernotfound:
        movq $0, %rax
        ret
    end:
        ret
