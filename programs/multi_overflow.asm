@500 125
@502 8000

CLR R0			//Akku = 0
INC				//Akku = 1
SWDD R0, #508	//Addr 508 = 1
LWDD R3, #508	//R3 = 1
ADDD #32767		//Akku = -32768
SWDD R0, #512	//Addr 512 = -32768
LWDD R2, #512	//R2 = -32768
LWDD R0, #500	//Akku = Number1
LWHILE1:		
BZD LEND		//while Number1 > 0 else Jump to End
AND R3			//Bitmask Number1 & 1
BZD LJPM1		//Jump to
LWDD R0, #506	//Akku = Total
SWDD R0, #518	//Addr 518 = Total
LWDD R1, #502	//R1 = Number2
ADD R1			//Akku = Akku + Number2
SWDD R0, #506	//Addr 506 = Total

LWDD R0, #502	//Akku = Number2
AND R2			//Bitmask pos/neg
BZD LPOS1		//Jump if pos
LWDD R0, #502	//Akku = Number2
DEC				//Akku -1
NOT				//Invert Akku
SWDD R3, #520
BD LJMP2		//Jump down
LPOS1:
LWDD R0, #502	//Akku = Number2
LJMP2:
SWDD R0, #516	//Addr 516 = positive Number2

LWDD R0, #506	//Akku = Total
AND R2			//Bitmask pos/neg
BZD LPOS2		//Jump if pos
LWDD R0, #506	//Akku = Total
DEC				//Akku -1
NOT				//Invert Akku
SWDD R3, #522
BD LJMP3		//Jump down
LPOS2:
LWDD R0, #506	//Akku = Total
LJMP3:
SWDD R0, #518

LWDD R0, #520
LWDD R1, #522
AND R1
BNZD LONE

LWDD R1, #516	//R1 = positive Number2
LWDD R0, #518
BD LADD

LONE:
ADDD #32767
BD LADDD

LADD:
ADD R1			//Akku = Akku + Total
LADDD:
LWDD R0, #504	//Akku = Result overflow
LWDD R1, #514	//R1 = Overflow Number2
BCD LNOR		//Overflow with number
ADD R1			//Add Overflow Number2
BCD LEND		//Overflow with number
BD LNRD			//Jump to Save
LNOR:
ADD R1			//Add Overflow Number2
BCD LEND		//Overflow with number
INC				//Add overflow
BCD LEND		//Overflow with number
LNRD:
SWDD R0, #504	//Save overflow result
LJPM1:
LWDD R0, #500	//Akku = Number1
SRA				//Half Akku
BZD LIF1		//Jump over carry check
SWDD R0, #500	//Write back new Number1
LWDD R0, #502	//Akku = Number2
SLL				//Double Akku (Check carry flag)
SWDD R0, #502	//Write back new Number2
LWDD R0, #514	//Get overflow Number2
BCD LNO2		//Overflow
SLA				//Double overflow Number
BCD LEND		//Overflow with number
BD LN2D			//Jump to Save
LNO2:
SLA				//Double overflow Number
BCD LEND		//Overflow with number
INC				//Add overflow
BCD LEND		//Overflow with number
LN2D:
SWDD R0, #514	//Addr 514 = overflow Number2
LWDD R0, #500	//Akku = Number1
LIF1:
BNZD LWHILE1	//If Number1 != 0 Jump back to while loop
LEND:
END