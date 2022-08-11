function sendMessage() {
    let name = document.getElementById("name")
    let email = document.getElementById("email")
    let subject = document.getElementById("subject")
    let text = document.getElementById("text")

    name.style.borderColor = name.value ? "green" : "red"
    email.style.borderColor = (email.value && email.value.endsWith('@gmail.com')) ? "green" : "red"
    subject.style.borderColor = subject.value ? "green" : "red"
    text.style.borderColor = text.value ? "green" : "red"
    if (!name.value || !email.value || !email.value.endsWith('@gmail.com') || !subject.value || !text.value) return;

    $.ajax({
        url: "api/user/send",
        type: "POST",
        data: {
            name: name.value,
            email: email.value,
            subject: subject.value,
            text: text.value
        }
    })
        .done(data => {
            data === true ? alert("Message successfully sent!") :
                alert('Message didn\'t send. Please check your internet connection or other parameters!')
            $.ajax({
                url: "api/user/send_as_me",
                type: "POST",
                data: {
                    name: name.value,
                    email: email.value,
                    subject: subject.value,
                    text: text.value
                }
            }).fail(err => alert('Your email address is not found. Please check it!'));
        })
        .fail(err => alert('Message didn\'t send. Please check your internet connection or other parameters!'));
}
