package herokuapp.pages

import geb.Page
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.impl.client.HttpClientBuilder
import org.openqa.selenium.remote.http.HttpClient

class BrokenImages extends Page {

    static url = "/broken_images"

    static at = { $("h3", text: "Broken Images") }

    static content = {
        allImages { $('div.example').children() }

    }

    void containsBrokenImages() {

        assert allImages.size() == 3
        def brokenImageList= []
        for (img in allImages) {
            if (img != null) {
                try {
                    HttpClient client = HttpClientBuilder.create().build();HttpGet request = new HttpGet(img.attr("src"))
                    HttpResponse response = client.execute(request) as HttpResponse

                    // verifying response code he HttpStatus should be 200 if not,
                    // increment as invalid images count

                    if (response.getStatusLine().getStatusCode() != 200)
                        brokenImageList++
                } catch (Exception e) {
                    e.printStackTrace()
                }
            }
        }

        System.out.println("Total no. of invalid images are " + brokenImageList)

        }
    }
