## Problem:
* Programmet avslutas av någon anledning vid felaktig gissning. - LÖST: "in" scannern stängdes på fel ställe.
* Programmet läser in å, ä och ö som ?. - LÖST: UTF_8 bytte ut å, ä och ö mot ? i windows terminalen vilket skapade problemet. Det gick att lösa genom att istället använda Codepage 850.
* Programmet ska kolla om ett tecken från gissningen finns någonstans i det slumpade ordet, men det gör inte det på korrekt sätt vilket skapar problem. - LÖST: Använd Arrays inbyggda contains funktion.