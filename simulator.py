from time import sleep
from json import dumps
from kafka import KafkaProducer

def main():
	producer = KafkaProducer(bootstrap_servers=['192.168.160.103:13092'], value_serializer=lambda x: dumps(x).encode('utf-8'))
	#producer = KafkaProducer(bootstrap_servers=['localhost:9092'], value_serializer=lambda x: dumps(x).encode('utf-8'))
	with open("node_data_2018_10_08____2018_10_14.csv", encoding='utf-8') as f:
		for line in f:
			print(line)
			values = [v.rstrip() if v != '' else -1 for v in line.split(',')]

			if(values[0] != 'id'):
				data = {'id': values[0], 'node_id': values[1], 'location_id': values[2], 'head': values[3],
						'lon': values[4], 'lat': values[5], 'speed': values[6], 'ts': values[7], 'write_time': values[8]}
				producer.send('topic1', value=data)

			sleep(1)

if __name__ == "__main__":
	main()