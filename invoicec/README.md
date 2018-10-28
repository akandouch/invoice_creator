# Release note v.0.0.2
- dashboard statistics
- login mechanism
- email generation
- uploads / attachments
- design refinement
- bug fixing
- invoice number
- devops optimization
- settings

# Release note v.0.0.1
- invoice profile
- invoice preview
- items
- pdf gen
- calendar

# Docker
docker build -t invoice/invoice-back .

docker run -p 8080:8080 -d --name=InvoiceBackend invoice/invoice-back

