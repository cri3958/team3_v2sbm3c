<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9093/</title>
<link rel="shortcut icon" href="/images/shortcut.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<link  href="https://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.js"></script>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<script type="module" src=""></script>

<script src="https://cdn.jsdelivr.net/npm/@tensorflow/tfjs@latest/dist/tf.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@teachablemachine/image@latest/dist/teachablemachine-image.min.js"></script>
<script type="text/javascript">
    // More API functions here:
    // https://github.com/googlecreativelab/teachablemachine-community/tree/master/libraries/image

    // the link to your model provided by Teachable Machine export panel
    const URL = "./tensorflow/";

    let model, webcam, labelContainer, maxPredictions;

    // Load the image model and setup the webcam
    async function init() {
        const modelURL = URL + "model.json";
        const metadataURL = URL + "metadata.json";

        // load the model and metadata
        // Refer to tmImage.loadFromFiles() in the API to support files from a file picker
        // or files from your local hard drive
        // Note: the pose library adds "tmImage" object to your window (window.tmImage)
        model = await tmImage.load(modelURL, metadataURL);
        maxPredictions = model.getTotalClasses();

        // append elements to the DOM
        document.getElementById("webcam-container").appendChild(webcam.canvas);
        labelContainer = document.getElementById("label-container");
        for (let i = 0; i < maxPredictions; i++) { // and class labels
            labelContainer.appendChild(document.createElement("div"));
        }
    }

    async function loop() {
        webcam.update(); // update the webcam frame
        await predict();
        window.requestAnimationFrame(loop);
    }

    // run the webcam image through the image model
    async function predict() {
        // predict can take in an image, video or canvas html element
        const prediction = await model.predict(webcam.canvas);
        for (let i = 0; i < maxPredictions; i++) {
            const classPrediction =
                prediction[i].className + ": " + prediction[i].probability.toFixed(2);
            labelContainer.childNodes[i].innerHTML = classPrediction;
        }
    }
</script>
</head>
<body>
<c:import url="/menu/top.do" />

<div style='margin-top:10px;'>
  <div style='margin: 0px auto;'>
  <!-- Fotorama data-ratio="100%/66%" -->
  <div class="fotorama"
         data-autoplay="1000"
         data-nav="thumbs"
         data-ratio="800/520"
         data-width="100%"
         data-height="200"
         data-fit="contain"
         data-transition="crossfade"
         data-keyboard="true"
         data-navposition="top"
         data-loop="true">
    <img src="/jquery/fotorama/images/winter01.jpg"> <!-- static 폴더 기준 -->     
    <img src="/jquery/fotorama/images/winter02.jpg">
    <img src="/jquery/fotorama/images/winter03.jpg">
    <img src="/jquery/fotorama/images/winter04.jpg">
    <img src="/jquery/fotorama/images/winter05.jpg">
    <img src="/jquery/fotorama/images/winter06.jpg">
    <img src="/jquery/fotorama/images/winter07.jpg">
    <img src="/jquery/fotorama/images/winter08.jpg">
    <img src="/jquery/fotorama/images/winter09.jpg">
    <img src="/jquery/fotorama/images/winter10.jpg">   
  </div>
</div>

<div id="drop-area" style="width: 300px; height: 200px; border: 2px solid; text-align: center; padding: 20px; margin:0px auto; margin-top:10px;">
  <input type="file" id="file-input">
</div>




<jsp:include page="./menu/bottom.jsp" flush='false' /> 
</body>

</div>
</html>
