INC				//Akku = 1
SWDD R0, #508	//Addr 508 = 1
LWDD R3, #508	//R3 = 1
ADDD #32767		//Akku = -32768
SWDD R0, #512	//Addr 512 = -32768
LWDD R2, #512	//R2 = -32768
LWDD R0, #500	//Akku = Number1
AND R2			//Bitmask pos/neg
SWDD R0, #510	//Addr 510 = Bitmask Number1
BZD LONEEV		//Jump if Number1 positive
LWDD R0, #500	//Akku = Number1
DEC				//Akku -1
NOT				//Invert Akku
SWDD R0, #500	//Addr 500 = Number1 positive
LONEEV:
LWDD R0, #502	//Akku = Number2
AND R2			//Bitmask pos/neg
BZD LTOWEV		//Jump if Number2 positive
LWDD R1, #510	//R1 = Bitmask Number1
AND R1			//Bitmask R1
NOT				//Invert Akku
AND R2			//Bitmask R2
SWDD R0, #510	//Addr 510 = Result pos/neg
LWDD R0, #502	//Akku = Number2
DEC				//Akku -1
NOT				//Invert Akku
SWDD R0, #502	//Addr 502 = Number2 positive
LTOWEV:
LWDD R0, #500	//Akku = Number1
LWHILE1:		
BZD LEND		//while Number1 > 0 else Jump to End
AND R3			//Bitmask Number1 & 1
BZD LJPM1		//Jump to
LWDD R0, #506	//Akku = Total
LWDD R1, #502	//R1 = Number2
ADD R1			//Akku = Akku + Number2 (Check carry flag)
SWDD R0, #506	//Addr 506 = Total
LWDD R0, #504	//Akku = Result overflow
LWDD R1, #514	//R1 = Overflow Number2
BCD LNOR		//Overflow with number
ADD R1			//Add Overflow Number2
BCD LOVER		//Overflow with number
BD LNRD			//Jump to Save
LNOR:
ADD R1			//Add Overflow Number2
BCD LOVER		//Overflow with number
INC				//Add overflow
BCD LOVER		//Overflow with number
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
BCD LOVER		//Overflow with number
BD LN2D			//Jump to Save
LNO2:
SLA				//Double overflow Number
BCD LOVER		//Overflow with number
INC				//Add overflow
BCD LOVER		//Overflow with number
LN2D:
SWDD R0, #514	//Addr 514 = overflow Number2
LWDD R0, #500	//Akku = Number1
LIF1:
BNZD LWHILE1	//If Number1 != 0 Jump back to while loop
LEND:
LWDD R0, #510	//Akku = Result pos/neg
BZD LOVER		//If Result pos Jump to End
LWDD R0, #506	//Akku = Result lower part
BZD ZERO		//Jump If lower part is Zero
DEC				//Akku -1
NOT				//Invert Akku
SWDD R0, #506	//Addr 506 = negative lower part of Result
LWDD R0, #504	//Akku = Result higher part
NOT				//Invert Akku
BD LSAVE		//Jump to save
ZERO:
LWDD R0, #504	//Akku = Result higher part
DEC				//Akku -1
NOT				//Invert Akku
LSAVE:
SWDD R0, #504	//Addr 504 = Result higher part
LOVER:
END
