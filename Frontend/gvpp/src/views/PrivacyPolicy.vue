<template>

  <div class="min-h-screen flex flex-col bg-slate-200">
    <HeaderComponent/>

    <main class="mx-15 my-5 flex flex-col gap-6">

      <section class="flex flex-col gap-5 text-lg mt-10">
          <div>
            <h2 class="text-4xl font-bold mt-5 mb-5">Privacybeleid</h2>

              <p>
                Ons platform is ontworpen om gebruikers betrouwbare en toegankelijke informatie op een gebruiksvriendelijke manier te bieden.
                We streven ernaar processen te vereenvoudigen en de toegang tot relevante diensten te verbeteren, terwijl we een veilige en transparante ervaring garanderen.
              </p>
          </div>
          <div>
              <h3 class="font-black">Gegevensverzameling</h3>
              <p>
                We verzamelen persoonlijke gegevens zoals naam en e-mailadres wanneer gebruikers zich aanmelden of contact met ons opnemen. Daarnaast verzamelen we gebruiksgegevens om te begrijpen hoe bezoekers het platform gebruiken, zodat we functionaliteit en de algemene gebruikerservaring kunnen verbeteren.
              </p>
          </div>
          <div>
              <h3 class="font-black">Gebruik van gegevens</h3>
              <p>
                Verzamelde gegevens worden gebruikt om updates te communiceren, diensten te verbeteren en veiligheid te waarborgen. We delen geen persoonlijke informatie met derden, tenzij wettelijk vereist.
              </p>
          </div>
          <div>
            <h3 class="font-black">Locatiegegevens</h3>
            <p>
              Met jouw toestemming kunnen we locatiegegevens verzamelen en verwerken om beschikbare plaatsen in jouw buurt te tonen.
            </p>
        </div>
          <div class="mb-10">
              <h3 class="font-black">Jouw rechten</h3>
              <p>
                  Je hebt het recht om toegang tot je persoonlijke gegevens aan te vragen, onjuistheden te corrigeren of verwijdering aan te vragen. Voor vragen kun je contact met ons opnemen.
              </p>
          </div>
      </section>

      <h2 class="text-xl font-black">Gebruiksvoorwaarden</h2>
      <section class="flex flex-col gap-3 text-m">
          <div>
              <h3 class="font-black">Aanvaarding van voorwaarden</h3>
              <p>
                Door toegang te verkrijgen tot of gebruik te maken van [Generiek Vrije Plaatsen Platform] ("het GVPP"), ga je akkoord met deze Gebruiksvoorwaarden en ons Privacybeleid. Als je niet akkoord gaat, gebruik het Platform dan niet.
              </p>
          </div>
          <div>
              <h3 class="font-black">Gebruik van het platform</h3>

              <ul>
                <li>Je stemt ermee in het Platform alleen te gebruiken voor wettige doeleinden en op een manier die de rechten van anderen niet schaadt of hun gebruik en plezier van het Platform niet beperkt.</li>
                <li>Je stemt ermee in het Platform niet te misbruiken door de normale werking te verstoren of er toegang toe te krijgen via een andere methode dan de interface en instructies die worden verstrekt.</li>
                <li>Je bent verantwoordelijk voor het vertrouwelijk houden van je accountinformatie en voor alle activiteiten onder jouw account.</li>
              </ul>

          </div>
          <div>
              <h3 class="font-black">Boekingen en beschikbaarheid</h3>
              <ul>
                <li>GVPP biedt informatie over beschikbare plaatsen om te boeken bij verschillende partnerorganisaties.</li>
                <li>Beschikbaarheid en boekingsbevestiging hangen af van de partnerorganisaties en zijn onderworpen aan hun voorwaarden.</li>
                <li>We garanderen niet de juistheid of volledigheid van beschikbaarheidsinformatie en zijn niet verantwoordelijk voor boekingsfouten of annuleringen.</li>
              </ul>
          </div>

          <div>
            <h3 class="font-black">Intellectueel eigendom</h3>
            <ul>
              <li>Alle inhoud op GVPP, inclusief tekst, afbeeldingen, logo’s en software, is eigendom van of in licentie gegeven aan [GVPP] en beschermd door intellectuele eigendomswetten.</li>
              <li>Beschikbaarheid en boekingsbevestiging hangen af van de partnerorganisaties en zijn onderworpen aan hun voorwaarden.</li>
              <li>We garanderen niet de juistheid of volledigheid van beschikbaarheidsinformatie en zijn niet verantwoordelijk voor boekingsfouten of annuleringen.</li>
            </ul>
        </div>

        <div>
          <h3 class="font-black">Contactgegevens</h3>
          <p>Als je vragen hebt over deze voorwaarden, neem dan contact met ons op via:
            📧 [support@[platform].com]
            🏢 [Bedrijfsnaam], [Adres]

            </p>
      </div>
      </section>

      <h2 class="text-xl font-black">Cookiebeleid</h2>
      <section class="flex flex-col gap-3 text-m">
          <div>
              <h3 class="font-black">Wat zijn cookies?</h3>
              <p>
                Cookies zijn kleine tekstbestanden die op je apparaat (computer, smartphone of tablet) worden opgeslagen wanneer je een website bezoekt. Ze helpen de website jouw voorkeuren te onthouden en je ervaring te verbeteren.
              </p>
          </div>
          <div>
              <h3 class="font-black">Hoe wij cookies gebruiken</h3>
              <p>
                  Je kunt je browserinstellingen aanpassen om cookies te beheren of uit te schakelen, maar dit kan de functionaliteit van de website beïnvloeden.
              </p>
          </div>
          <div>
              <h3 class="font-black">Beleidswijzigingen</h3>
              <p>
                  Wijzigingen in dit beleid worden op onze website geplaatst om je op de hoogte te houden.
              </p>
          </div>
      </section>
    </main>

</div>
</template>

<script setup lang="ts">
import HeaderComponent from '@/components/HeaderComponent.vue';
</script>
