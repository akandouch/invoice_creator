docker build -t invoice/invoice-back invoice-back

docker run -p 8080:8080 -d --name=InvoiceBackend invoice/invoice-back