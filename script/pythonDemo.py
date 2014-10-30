#!/usr/bin/python
import sys
x  = 5
y = "hello world"
z = '''welcome \
everyone'''

print y,z

if x==5:
	print "x",x

while x > 3:
	print "loop",x
	x=x-1
else:
	print "done"

def say(mes):
	print "say",mes
say("hello")

print say("hello")

for i in sys.argv:
	print i

print sys.copyright

print sys.path

