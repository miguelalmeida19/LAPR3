# Pos = (X)*z + y*(X*Y) + x*(X*Y*Z)

#x está no %edi
#y está no %esi
#z está no %edx

.section .data
    .global X
    .global Y
    .global Z
    .global cargo #matriz
    .global capacity #total de elementos na matriz

    .global ptr1

.section .text

.global check_if_container_is_there


    # Para se obter a posição onde vamos procurar, usamos a seguinte fórmula, que desenvolvemos
    # Pos = (X)*z + y*(X*Y) + x*(X*Y*Z)

    check_if_container_is_there:

    #limpar registos
    movq $0, %rax
    movq $0, %rcx
    movq $0, %r8
    movq $0, %r9
    movq $0, %r10       

    movl capacity(%rip), %r10d          # move a capacidade do ship para %r10d

    movl X(%rip), %r8d                  # move o X (constante definida no c) para %r8d
    imull %r8d, %edx                    # multiplica X por x

    movl Y(%rip), %r9d                  # move o Y (constante definida no c) para %r9d
    imull %r9d, %esi                    # multiplica y por Y
    imull %r8d, %esi                    # multiplica X por y

    addl %edx, %esi                     # faz a soma das duas contas feitas (X)*z + y*(X*Y)

    imull %edi, %r10d                   # multiplica x pela capacidade do ship que é igual a (X*Y*Z)

    addl %r10d, %esi                    # junta tudo somando (X)*z + y*(X*Y) + x*(X*Y*Z)

    leaq cargo(%rip), %rcx              # passa a matriz para o registo %rcx

    addq %rsi, %rcx                     # vai à posição calculada anteriormente

    cmpl $0, (%rcx)                     # verifica se tem um 0 nessa posição
    je no_number

    movl $1, %eax                       # se tiver um número diferente de 0, retorna 1
    ret

    no_number:                          # retorna o valor 0
    ret

.global count
    count:

    #limpar registos
    movq $0, %rax #valor a retornar
    movq $0, %rcx #pointer
    movq $0, %rdi #x
    movq $0, %rsi #y
    movq $0, %rdx #z
    movq $0, %r8 #contador
    movq $0, %r9 #increser
    movq $0, %r10 #Numero total de numeros

    #mover os valores para os registos
    movq ptr1(%rip), %rcx #pointer
    movq $3, %r10 #Numero de linhas - 3 linhas
    imulq $3, %r10 #Multiplicar pelas colunas - 3 colunas

    jmp ciclo

    ciclo:

    cmpq %r9, %r10
    je end

    movl (%rcx,%r9,4), %edi #Valor de X
    incq %r9

    movl (%rcx,%r9,4), %esi #Valor de Y
    incq %r9

    movl (%rcx,%r9,4), %edx #Valor de Z
    incq %r9

    pushq %r8
    pushq %r9
    pushq %r10
    pushq %rcx

    call check_if_container_is_there

    popq %rcx
    popq %r10
    popq %r9
    popq %r8

    cmpq $1, %rax
    je contador

    jmp ciclo

    contador:
    incq %r8
    jmp ciclo

    end:
    movq %r8, %rax
    ret