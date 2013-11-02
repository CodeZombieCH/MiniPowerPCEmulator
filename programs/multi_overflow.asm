CLR R0			//Akku = 0
INC				//Akku = 1
SWDD R0, #508	//Addr 508 = 1
LWDD R3, #508	//R3 = 1
ADDD #32767		//Akku = -32768
SWDD R0, #512	//Addr 512 = -32768
LWDD R2, #512	//R2 = -32768
LWDD R0, #500	//Akku = Number1
BZD #190		//while Number1 > 0 else Jump to End
AND R3			//Bitmask Number1 & 1
BZD #152		//Jump to
LWDD R0, #506	//Akku = Total
LWDD R1, #502	//R1 = Number2
ADD R1			//Akku = Akku + Number2 (Check carry flag)
SWDD R0, #506	//Addr 506 = Total
LWDD R0, #504	//Akku = Result overflow
LWDD R1, #514	//R1 = Overflow Number2
BCD #142		//Overflow with number
ADD R1			//Add Overflow Number2
BCD #190		//Overflow with number
BD #150			//Jump to Save
ADD R1			//Add Overflow Number2
BCD #190		//Overflow with number
INC				//Add overflow
BCD #190		//Overflow with number
SWDD R0, #504	//Save overflow result
LWDD R0, #500	//Akku = Number1
SRA				//Half Akku
SWDD R0, #500	//Write back new Number1
LWDD R0, #502	//Akku = Number2
SLA				//Double Akku (Check carry flag)
SWDD R0, #502	//Write back new Number2
BZD #188		//Jump over carry check
LWDD R0, #514	//Get overflow Number2
BCD #176		//Overflow
SLA				//Double overflow Number
BCD #190		//Overflow with number
BD #184			//Jump to Save
SLA				//Double overflow Number
BCD #190		//Overflow with number
INC				//Add overflow
BCD #190		//Overflow with number
SWDD R0, #514	//Addr 514 = overflow Number2
LWDD R0, #500	//Akku = Number1
BNZD #116		//If Number1 != 0 Jump back to while loop
END