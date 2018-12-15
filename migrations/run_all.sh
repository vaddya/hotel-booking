#!/bin/bash
scripts="$(ls *.sql)"
for script in ${scripts}
do
    sudo -u postgres psql \
         -U postgres \
         -d hotel_booking \
         -f ${script}
done