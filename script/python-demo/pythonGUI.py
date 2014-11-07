#!/usr/bin/python  
from Tkinter import *  
import sys   
class PPCU(object):  
    def __init__(self):  
        self.top=Tk()  
        self.top.title('vKsir01.01')  
  
        self.nameVar=StringVar()  
        self.nameVar.set('Name')  
        frame=Frame(self.top)  
      
        nameLabel=Label(frame,text="Name:",underline=0)  
        nameEntry=Entry(frame,textvariable=self.nameVar)  
        cancelButton=Button(frame,text="Cancel",command=self.close)  
        nameLabel.grid(row=0,column=0,sticky=W,pady=3,padx=3)  
        nameEntry.grid(row=0,column=1,sticky=EW,pady=3,padx=3)  
        cancelButton.grid(row=2,column=3,sticky=EW,pady=3,padx=3)  
      
        frame.pack()  
  
    def close(self,event=None):  
        quit()  
def main():  
    PPCU()  
    mainloop()  
if __name__=='__main__':  
    main()  
