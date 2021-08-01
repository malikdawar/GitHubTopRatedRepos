# Github trending

A clean example to play with the local and the remoate data sources, uesd github trending API for the data source. Focused on the Kotlin coroutines, MVVM with repository layer, data source (Usecase) layer and Kotlin generics with the help of Hilt. Used mockk and Junit 4 for the testing purpose.


- **Language**
    • Used **Kotlin** to develop the application with the power of **kotlin coroutines** for the task managemant, for more please visit https://developer.android.com/courses/pathways/android-coroutines
    
 - **Architecture**
    • Used **MVVM + Repository pattern** for design the architectural pattern, for more about MVVM visit https://medium.com/hongbeomi-dev/create-android-app-with-mvvm-pattern-simply-using-android-architecture-component-529d983eaabe 
    
 - **Architecture Flow**
     • **Request/Update from/to view(Fragmnet/Activity) <--> ViewModel(Controller/request manipulation) <--> Repository(the end point before Datasource/Usecase that defines the source of data that could be API or local DB)  <-->  DataSource(the end point before DB) 
     <-1-> DBDao(Query managemnat) <--> Room DataBase() <-2-> API/Remote service **
    
 - **DataBase**
    • Used Room database for the app, poweredby google foe more please visit https://developer.android.com/topic/libraries/architecture/room?gclid=CjwKCAiAnvj9BRA4EiwAuUMDfyR7KAssy8H6-aNvZ4KfkK87I7pVv09bZ2ZOGU7iRnNPzR4bi0ncrBoCqR8QAvD_BwE&gclsrc=aw.ds **Major components** includes **1-DaocClass, 2-ModelClass, 3-DatabaseCalss**

- **DI Framework**
    • Used Hilt for as an dependency injection frame work, for more https://developer.android.com/training/dependency-injection/hilt-android
    
  
