Dear FreeAgent,

Please find in this repository, my coding test for exchange rates!

I'll try and give a brief breakdown of my thought process and what I was able to achieve, as I don't think the code itself can explain this alone. 

I intended to make the app one screen with one recycler view, and to swap entries for stage 1 (what I've referred to as standard mode, the conversion results screen) with entries for stage 2 (the 5 day comparison screen). I've used a pretty good example in the past for how to do this using Kotlin, here: https://proandroiddev.com/flexible-recyclerview-adapter-with-mvvm-and-data-binding-74f75caef66a. It's a great example for how to use different view types in a recycler view, and my hope was to adapt this to the 2 different modes described above.

Additionally, I had started with the ambition of one call to the Fixer API to get all of the rates for the past 5 days upfront. This way I could make one expensive rest call and then do the conversions and formatting offline. Alas, my inexperience with the Moshi api prevented this - I couldn't work out how to parse nested objects or dynamic keys (each date of currency-exchange maps), despite reading the official docs! I had to u-turn on this ambition and simply get back one map for the current date.

In the end, I was only able to display the conversions multiplied by 100 on the recycler view. My inexperience with a few things still in Kotlin was holding me back here. For instance, in Xamarin, most Viewmodel implementations are observable by default, making them easy to set up with data-binding. In Kotlin, you either choose a ViewModel or BaseObservable as your ViewModel's base. To get both while still using the default viewModels factory seems to require a bit of engineering with observables that I'm just not component enough with yet. 

Finally, for Unit Testing, though I have worked with Unit Testing in previous projects, my experience with JUnit and Mockito is foggy at best. But not having enough ViewModel or utility code available to me meant not a lot was ready for me to test. With more time, I wanted to test proper exception handling for API calls, malformed JSon responses, and reliability of the data structures and mathematics being used on the currencies.

Hopefully my transparency here has been helpful. I'm very aware of the things I still need to improve on, not just in Kotlin, but as a developer. Honestly, coding tests are always quite tricky for me. When I'm being given requirements for a real project, it's clear what I have to do and I can often do it very well, even with time constraints. For tests, I bring this same ambition, which is ultimately a detriment, given these tests are normally meant to take 4 hours. I want to be good at these tests, but more importantly, I'm trying to be a good well-rounded developer at this point in my career. 

I would welcome any feedback you have on where I can improve.

Thanks for your time!