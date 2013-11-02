CLR R0			//Akku = 0
SWDD R0, #504	//Addr 504 = 0
SWDD R0, #506	//Addr 506 = 0
INC				//Akku = 1
SWDD R0, #508	//Addr 508 = 1
LWDD R3, #508	//R3 = 1
ADDD #32767		//Akku = -32768
SWDD R0, #512	//Addr 512 = -32768
LWDD R2, #512	//R2 = -32768
LWDD R0, #500	//Akku = Number1
BZD #192		//while Number1 > 0 else Jump to End
AND R3			//Bitmask Number1 & 1
BZD #154		//Jump to
LWDD R0, #504	//Akku = Total
LWDD R1, #502	//R1 = Number2
ADD R1			//Akku = Akku + Number2 (Check carry flag)
SWDD R0, #506	//Addr 506 = Total
LWDD R0, #504	//Akku = Result overflow
BCD #144		//Overflow with number
SLA				//Double overflow Number
BCD #192		//Overflow with number
BD #152		//Jump to Save
SLA				//Double overflow Number
BCD #192		//Overflow with number
INC				//Add overflow
BCD #192		//Overflow with number
SWDD R0, #504	//Save overflow result
LWDD R0, #500	//Akku = Number1
SRA				//Half Akku
SWDD R0, #500	//Write back new Number1
LWDD R0, #502	//Akku = Number2
SLA				//Double Akku (Check carry flag)
SWDD R0, #502	//Write back new Number2
BZD #190		//Jump over carry check
LWDD R0, #512	//Get overflow Number2
BCD #178		//Overflow
SLA				//Double overflow Number
BCD #192		//Overflow with number
BD #186		//Jump to Save
SLA				//Double overflow Number
BCD #192		//Overflow with number
INC				//Add overflow
BCD #192		//Overflow with number
SWDD R0, #512	//Addr 512 = overflow Number2
LWDD R0, #500	//Akku = Number1
BNZD #120	//If Number1 != 0 Jump back to while loop
END