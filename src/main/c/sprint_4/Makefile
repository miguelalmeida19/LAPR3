main: main.o US409.o US410ASM.o US410.o US411.o
	gcc -g -Wall main.o US409.o US410ASM.o US410.o US411.o -o main -lm

US410.o: US410.c
	gcc -Wall -g -c US410.c -o US410.o

US410ASM.o: US410ASM.s
	gcc -Wall -g -c US410ASM.s -o US410ASM.o

US409.o: US409.c
	gcc -Wall -g -c US409.c -o US409.o

US411.o: US411.c
	gcc -Wall -g -c US411.c -o US411.o

main.o: main.c
	gcc -g -Wall -c main.c -o main.o
clean:
	rm -f *.o main
run: main
	./main $(ARGS)
