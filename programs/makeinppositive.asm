LWDD R2, 512 	// 0b10000.. load bitmask to R2
LWDD R0, 500 	// input #1
AND R2			// binary AND Accu and R2 (is inp. negative?)
SWDD R0, 510	// save result to 510
BZ #inponeisev	// jump if input #1 is positive
LWDD R0, 500	// load negative input
DEC			
NOT
SWDD R0, 500	// save positive as input 
:inponeisev 
LWDD R0, 502	// load input #2
AND R2			// binary AND Accu and R2 (is inp. negative?)
bz #inptwoisev
LWDD R1, 510	// load former status to R1
AND R1			// is result pos (0) or negative 100..)
SWDD R1, 510	// save result to 510
LWDD R0, 502	// load negative input
DEC
NOT
SWDD R0, 502	// save positive as input
:inptwoisev
