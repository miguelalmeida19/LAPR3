main: asm.o main.o US314.o
	gcc -Wall asm.o main.o US314.o -o main
	cp main ../../../bin/main  #this line enables the autocopy of the binary that was created to the bin folder

US314.o: US314.s
	gcc -Wall -c US314.s -o US314.o
asm.o: asm.s
	gcc -g -Wall -c asm.s -o asm.o
main.o: main.c
	gcc -g -Wall -c main.c -o main.o
clean:
	rm -f *.o main
run: main
	./main
