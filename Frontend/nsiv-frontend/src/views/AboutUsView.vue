<template>
<!-- Over ons - mobile op Figma-->
    
    
    <main class="mx-5 my-4 flex flex-col gap-3"><!--margin naar app.vue zetten-->
        <nav class="flex justify-between items-center">
            <h1 class="text-2xl font-bold">
                Over ons
            </h1>
            <p>burger</p>
        </nav>
        <h2 class="text-lg font-bold">
            Missie en visie
        </h2>
        <section class="flex flex-col gap-3 text-sm">  
            <div>
                <h3 class="font-black">
                    Missie
                </h3>
                <p>
                    Bij "Naar School in Vlaanderen" is onze missie om ouders en scholen in Vlaanderen te verbinden door middel van een gebruiksvriendelijk en informatief platform. Wij streven ernaar om ouders te helpen bij het vinden van de beste onderwijsopties voor hun kinderen, door uitgebreide en betrouwbare informatie te bieden over scholen, studierichtingen en ondersteuningsmogelijkheden. Ons doel is om het proces van schoolkeuze en inschrijving transparanter, eenvoudiger en stressvrij te maken voor alle betrokkenen.
                </p>
            </div> 
            <div>
                <h3 class="font-black">
                    Visie
                </h3>
                <p>
                    Onze visie is een onderwijsomgeving waarin elke ouder gemakkelijk toegang heeft tot de informatie die nodig is om weloverwogen beslissingen te nemen over de schoolkeuze van hun kinderen. Wij geloven in gelijke kansen voor alle kinderen, ongeacht hun achtergrond of speciale behoeften, en streven ernaar om inclusiviteit en diversiteit binnen het onderwijs te bevorderen. Door een centraal platform te bieden dat ouders, scholen en gemeenschappen samenbrengt, willen wij bijdragen aan een sterkere, beter geïnformeerde en meer betrokken onderwijsgemeenschap in Vlaanderen.
                </p>
            </div>
            <div>
                <h3 class="font-black">Onze Waarden</h3>
                <ol class="list-decimal list-outside pl-6">
                    <li><span class="font-bold italic">Transparantie: </span>Wij zijn toegewijd aan het bieden van duidelijke, nauwkeurige en actuele informatie over scholen en onderwijsopties.</li>
                    <li><span class="font-bold italic">Toegankelijkheid: </span>We streven ernaar om ons platform gebruiksvriendelijk en toegankelijk te maken voor alle ouders, inclusief anderstalige ouders en ouders van kinderen met speciale behoeften.</li>
                    <li><span class="font-bold italic">Betrouwbaarheid: </span>We garanderen dat de informatie op ons platform afkomstig is van betrouwbare bronnen en regelmatig wordt bijgewerkt.</li>
                    <li><span class="font-bold italic">Gemeenschap: </span>We geloven in de kracht van samenwerking en ondersteunen een actieve gemeenschap van ouders, leraren en schooldirecteuren die kennis en ervaringen delen.</li>
                    <li><span class="font-bold italic">Ondersteuning: </span>We zijn hier om ouders te helpen en te begeleiden bij elke stap van het proces, van het zoeken naar scholen tot het aanmelden en inschrijven van hun kinderen.</li>
                </ol>
            </div>
            <p>
                Bij "Naar School in Vlaanderen" zetten we ons in om ouders te empoweren met de tools en informatie die ze nodig hebben om de best mogelijke onderwijservaringen voor hun kinderen te realiseren. Samen bouwen we aan een betere toekomst voor de volgende generatie.
            </p>
        </section>

        <h2 class="text-lg font-bold">Team</h2>
        <section class="flex flex-col gap-3  text-xs">
                <div class="flex items-top"><!--v-for gebruiken met dynamische info-->
                    <img class="w-30 h-30 bg-gray-600">
                    <p>
                        Emma Janssen is de projectleider van "Naar School in Vlaanderen". Met een achtergrond in onderwijskunde en meer dan tien jaar ervaring in het onderwijsveld, zorgt Emma voor de algehele coördinatie en uitvoering van het project. Ze houdt toezicht op de ontwikkeling van het platform en zorgt ervoor dat de missie en visie van het project worden nageleefd.
                    </p>
                </div>
                <div class="flex items-center">
                    <img class="w-30 h-30 bg-gray-600">
                    <p>
                        Lucas De Vries is de technisch directeur van "Naar School in Vlaanderen". Hij heeft een uitgebreide achtergrond in softwareontwikkeling en IT-beheer. Lucas is verantwoordelijk voor de technische aspecten van het platform, inclusief de ontwikkeling en het onderhoud van de website en de database.
                    </p>
                </div>
        </section>

        <h2 class="text-lg font-bold">Contact</h2>
        <section class="flex flex-col gap-3 text-sm">
            <p>tel</p>
            <p>email</p>
        </section>

        <h2 class="text-lg font-bold">Volg ons</h2>
        <section class="flex flex-col gap-3 text-sm">
            <ul>
                <li>
                    <p>social icons</p>
                </li>
            </ul>
        </section>
    </main>
</template>

<script setup lang="ts">

</script>