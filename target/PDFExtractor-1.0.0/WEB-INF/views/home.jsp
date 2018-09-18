<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PDFContent Extractor</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="stylesheet" media="all" href="<c:url value="/resources/plain.css"/>"/>
    </head>


    <style>
        .Detail{
            margin-left: 10px;
        }
        /*inline-block*/
        .inline-b {
            max-width:1200px;
            margin:0 auto;
        }
        .inline-b-item {
            display: inline-block;
        }

        /*Flexbox*/
        .flex {
            padding: 0;
            margin: 0;
            list-style: none;

            display: -webkit-box;
            display: -moz-box;
            display: -ms-flexbox;
            display: -webkit-flex;
            display: flex;

            -webkit-flex-flow: row wrap;
            justify-content: space-around;
        }
    </style>

    <body data-spy="scroll" data-target="#resumeNavigationScrollSpy" data-offset="70">
        <div class="container-fluid">
            <div class="row">
                <div class="col-xs-12 col-sm-4 col-md-3 resume-profile">

                    <img width="180" src="<c:url value="/resources/logo.png"/>" class="img-thumbnail" alt="Logo">

                    <h1>
                        PDF Content Extractor
                    </h1>


                    <ul class="list-unstyled">
                        <li>API Version: 1.0.0</li>


                        <li>By : <a href="http://kriit.com/">Kriit</a>
                        </li>
                        <li>Email: <a href="mailto://viralnavadiya@kriit.com">viralnavadiya@kriit.com</a></li>
                    </ul>
                </div>
                <!-- /.resume-profile -->

                <div class="col-xs-12 col-sm-8 col-md-6 resume-info">

                    <section class="resume-info-section">
                        <h2 id="introduction">Introduction</h2>
                        <p class="lead text-justify">
                            PDF Content Extractor will help you to extract content based on regular expression provided. You have to provide BASE 64 encoded string as PDF for the input.
                        </p>
                    </section>

                    <section class="resume-info-section">

                        <h2 id="#APIDoc">API Documentation</h2>

                        <div class="Detail">
                            <h4 id="SAMPLE_REQ">
                                Request Details
                            </h4>
                            <div class="resume-info-section-block">
                                <h5 id="SAMPLE_REQ">
                                    Sample Request 1
                                </h5>
                                <pre>{
	"regExList": [{
		"value": "(?([0-9]{3}\)?[-]([0-9]{3})[-]([0-9]{4})",
		"key": "Phone"
	},
	{
		"value": "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,3}",
		"key": "Email"
	}],
	"requestID": "abcderfhjshjgefgdf",
	"pdf": "JVBERi0xLjQKMyAwIG9iago8PC9UeXBlI...."
}
                                </pre>

                                <p>
                                    Here,
                                <ul>
                                    <li>URL: <small><%= request.getContextPath() %>/PARSE_PDF_ATTACHMENT</small></li>
                                    <li>requestID:	<small>Should be alphanumeric between 3 to 18.</small></li>
                                    <li>pdf:			<small>Valid Base-64 string.</small></li>
                                    <li>regExList:	<small>Maximum 3 request can be submitted for given PDF.</small></li>
                                </ul>
                                <p>
                            </div>


                            <div class="resume-info-section-block">
                                <h5 id="SAMPLE_RES">
                                    Sample Request 2
                                </h5>
                                <pre>{
	"regExList": [{
		"value": "(?([0-9]{3}\)?[-]([0-9]{3})[-]([0-9]{4})",
		"key": "Phone"
	},
	{
		"value": "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,3}",
		"key": "Email"
	}],
	"requestID": "abcderfhjshjgefgdf",
        "Type": "docx",
	"Content": "JVBERi0xLjQKMyAwIG9iago8PC9UeXBlI...."
}
                                </pre>
                                <p>
                                    Here,
                                <ul>
                                    <li>URL: <small><%= request.getContextPath() %>/PARSE_ATTACHMENT_BY_TYPE</small></li>
                                    <li>requestID:	<small>Shohuld be alphanumeric betwwn 3 to 18.</small></li>
                                    <li>Type:	<small>PDF/DOC/DOCX</small></li>
                                    <li>Content:			<small>Valid Base-64 string.</small></li>
                                    <li>regExList:	<small>Maximum 3 request can be submitted for given PDF.</small></li>
                                </ul>
                                <p>
                            </div>

                        </div>



                        <div class="Detail">
                            <h4 id="SAMPLE_RES">
                                Response Details
                            </h4>


                            <div class="resume-info-section-block">
                                <h5 id="SAMPLE_RES">
                                    Sample Response
                                </h5>
                                <pre>{
   "status":    {
      "responseCode": 200,
      "message": "Request submitted succesfully Kindly check the result.",
      "status": "OK"
   },
   "regExResult":    {
      "req_ID": "abcderfhjshjgefgdf",
      "result":       {
         "Email":          [
            "pqr.abc@gmail.com"
            "abcd.pqr@gmail.com"
         ],
         "Phone": []
      }
   }
}
                                </pre>
                                <p>
                                    Here,
                                <ul>
                                    <li>responseCode:	<small>200 for succesful submission and 500 for rest.</small></li>
                                    <li>status:			<small>['OK','ERROR'] based on input.</small></li>
                                </ul>
                                <p>
                            </div>

                        </div>



                    </section>


                </div>


                <div id="resumeNavigationScrollSpy" class="col-md-3">
                    <ul class="nav nav nav-pills nav-stacked resume-navigation hidden-print hidden-sm hidden-xs affix-top" data-spy="affix" data-offset-top="0">
                        <li>
                            <a href="#introduction">Introduction</a>
                        </li>
                        <li>
                            <a href="#APIDoc">API Documentation</a>
                            <ul class="nav nav nav-pills nav-stacked">
                                <li><a href="#SAMPLE_REQ">Sample request</a></li>
                                <li><a href="#SAMPLE_RES">Sample response</a></li>
                            </ul>
                        </li>
                    </ul>
                </div> 
            </div>            
        </div>
    </body>
</html>