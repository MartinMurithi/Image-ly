# Image-ly
An android application that consumes the Pixel API to display, search images, download and set wallpapers. 
Tools used include :
  1.Retrofit, to simplify my HTTP requests.
  2.Glide, to load images from pixels.
  3. Gson Converter, to deserialize json object to POJO
The app displays images in a recyclerview, when a user clicks on an image, a new fragment is opened where the image is viewed in an image view.
It laso uses view model, live data and mutable live data to share data within fragments.
