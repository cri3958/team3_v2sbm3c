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

<script src="https://cdn.jsdelivr.net/npm/@tensorflow/tfjs"></script>
<script>
        // Load the TensorFlow.js model
        async function loadModel() {
            const model = await tf.loadLayersModel('./tensorflow/model.json');
            return model;
        }

        // Function to handle image change
        async function handleImageChange() {
            const inputElement = document.getElementById('imageInput');
            const inputImageElement = document.getElementById('inputImage');
            const predictionResultElement = document.getElementById('predictionResult');

            // Load the selected image
            const file = inputElement.files[0];
            const imageUrl = URL.createObjectURL(file);
            inputImageElement.src = imageUrl;

            // Load the TensorFlow.js model
            const model = await loadModel();


            // Preprocess the image and make a prediction
            const image = await loadImage(imageUrl);
            const prediction = await predictImage(model, image);
            console.log(prediction);
            const word = prediction.split(",")[0].split("Class")[1].split("]")[1].split(" ").join("");
            let onclick = "location.href=";
            onclick += "'notices/list_by_search.do?page_now=1&word=";
            onclick += word;
            onclick +="'";
            // Display the prediction result
            predictionResultElement.innerHTML = "Prediction: "+prediction+"<br><button type='button' class='btn btn-secondary btn-sm' style='padding: 2px 8px 3px 8px; margin: 0px 0px 2px 0px;' onclick="+onclick+">공고 바로가기</button>  ";

        }

        // Function to load an image
        async function loadImage(imageUrl) {
            const image = new Image();
            return new Promise((resolve, reject) => {
                image.onload = () => resolve(tf.browser.fromPixels(image));
                image.onerror = (error) => reject(error);
                image.src = imageUrl;
            });
        }

        // Function to make a prediction
        async function predictImage(model, image) {
            // Preprocess the image if needed
            // For example, normalize pixel values to be between 0 and 1
            var normalizedImage = image.toFloat().div(255.0);
            // Reshape the image to match the input shape expected by your model
            // For example, if your model expects 224x224 RGB images:
            const reshapedImage = tf.image.resizeBilinear(normalizedImage, [224, 224]);
            const expandedImage = reshapedImage.expandDims();
            // Make a prediction using the loaded TensorFlow.js model
            const prediction = model.predict(expandedImage);
            // Process the prediction result as needed
            // For example, convert the prediction tensor to an array
            const predictionArray = Array.from(prediction.dataSync());
            console.log(predictionArray)
            // Find the index with the highest probability
            const maxIndex = predictionArray.indexOf(Math.max(...predictionArray));
            var petlist=["[개] 골든 리트리버","[개] 그레이 하운드","[개] 닥스훈트","[개] 도베르만","도사","[개] 라브라도 리트리버","[고양이] 러시안 블루","[고양이] 레그돌",
            "[개] 로트와일러","[개] 말라뮤트","[개] 말티즈","[개] 미니어쳐 핀셔","[개] 베들링턴 테리어","[고양이] 벵갈","[개] 보더 콜리","[개] 보스턴 테리어","[개] 불독","[고양이] 브리티시 쇼트헤어",
            "[개] 비글","[개] 비숑 프리제","[개] 사모예드","[개] 삽살개","[개] 샤페이","[고양이] 샴","[개] 셰퍼드","[개] 슈나우져","[고양이] 스코티시폴드","[개] 스피츠",
            "[고양이] 스핑크스","[개] 시바","[개] 시베리안 허스키","[개] 시츄","[개] 요크셔 테리어","[개] 웰시 코기","[개] 진도견","[개] 차우차우","[개] 치와와","[개] 코카 스파니엘",
            "[개] 푸들","[개] 퍼그","[고양이] 페르시안","[개] 포메라니안","[개] 프렌치 불독"]
            // Return the result (adjust as needed based on your model output)
            return "Index: "+maxIndex+" Class "+petlist[maxIndex]+", Probability: "+predictionArray[maxIndex].toFixed(4);
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

<div id="drop-area" style="width: 600px; height: 400px; border: 2px solid; text-align: center; padding: 20px; margin:0px auto; margin-top:10px;">
  <input type="file" id="imageInput" accept="image/*" onchange="handleImageChange()">
  <img id="inputImage" src="" alt="Selected Image" style="max-width: 300px;">
  <h2>Prediction:</h2>
    <div id="predictionResult"></div>
</div>




<jsp:include page="./menu/bottom.jsp" flush='false' /> 
</body>

</div>
<script >
    const dropbox = document.getElementById('drop-area');
    const file = document.getElementById('imageInput');

    //박스 안에 drag 하고 있을 때
    dropbox.addEventListener('dragover', function (e) {
    e.preventDefault();
    this.style.backgroundColor = 'rgb(13 110 253 / 25%)';
    });
  
    //박스 밖으로 drag가 나갈 때
    dropbox.addEventListener('dragleave', function (e) {
    this.style.backgroundColor = 'white';
    });
  
    //박스 안에 drop 했을 때
    dropbox.addEventListener('drop', function (e) {
    e.preventDefault();
    this.style.backgroundColor = 'white';
  
    //파일 이름을 text로 표시
    let filename = e.dataTransfer.files;
    console.log(filename);
    file.files = filename;
    handleImageChange();
  });
</script>
</html>
